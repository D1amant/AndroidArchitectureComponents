package com.example.luisfernandorodrigues.roompock.Dao.Migration;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;

public class NewsMigration {

    public static  final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE News "
                    + " ADD COLUMN image TEXT");
        }
    };
}
