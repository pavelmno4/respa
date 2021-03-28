package ru.pkozlov.respa.db.callback;

import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.pkozlov.respa.db.entity.EntityId;

@Component
@RequiredArgsConstructor
public class EntityIdCallback<T extends EntityId> implements BeforeConvertCallback<T> {
    private final DatabaseClient databaseClient;

    @Override
    public Publisher<T> onBeforeConvert(T entity, SqlIdentifier table) {
        if (entity.getId() == null) {
            return databaseClient.sql("SELECT nextval('respa." + table + "_seq')")
                    .map(row -> row.get(0, Long.class))
                    .first()
                    .doOnNext(entity::setId)
                    .map(id -> entity);
        }

        return Mono.just(entity);
    }
}
