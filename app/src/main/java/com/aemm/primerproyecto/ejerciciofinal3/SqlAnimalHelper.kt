package com.aemm.primerproyecto.ejerciciofinal3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * SqlAnimalHelper
 *
 * Clase que nos servirá hacer las operaciones CRUD de la tabla animal.
 */
class SqlAnimalHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "animal.db"
        private const val TBL_ANIMAL = "tbl_animal"
        private const val ID = "id"
        private const val NOMBRE = "nombre"
        private const val DESCRIPCION = "descripcion"
        private const val SEXO = "sexo"
        private const val IS_ENFERMO = "is_enfermo"
        private const val IMAGEN = "imagen"
    }

    /**
     * Método que crea las tablas necesarias de la Base de Datos
     *
     * @param database Instancia de la Base de Datos.
     */
    override fun onCreate(database: SQLiteDatabase?) {
        val sqlCreate = """
            CREATE TABLE $TBL_ANIMAL(
                $ID integer NOT NULL PRIMARY KEY AUTOINCREMENT,
                $NOMBRE text(32) NOT NULL,
                $DESCRIPCION text(255) NOT NULL,
                $SEXO text(6) NOT NULL,
                $IMAGEN text(255) NOT NULL,
                $IS_ENFERMO integer(1) NOT NULL,
                CONSTRAINT check_max_lenght_nombre CHECK (LENGTH($NOMBRE) <= 32),
                CONSTRAINT check_max_lenght_descripcion CHECK (LENGTH($DESCRIPCION) <= 255),
                CONSTRAINT check_max_lenght_imagen CHECK (LENGTH($IMAGEN) <= 255), 
                CONSTRAINT check_sexo CHECK ($SEXO IN ('Hembra','Macho')),
                CONSTRAINT check_is_enfermo CHECK ($IS_ENFERMO IN (0,1))
            )
        """.trimMargin()
        database?.execSQL(sqlCreate)
    }

    /**
     * Método que se llama cuando hay que actualizar la base de datos.
     * La implementación debería utilizar este método para eliminar tablas, añadir tablas o
     * hacer cualquier otra cosa que necesita para actualizar a la nueva versión del esquema.
     *
     * @param database Instancia de la Base de Datos.
     * @param oldVersion La versión vieja de la Base de Datos.
     * @param newVersion La versión nueva de la Base de Datos.
     */
    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sqlUpdate = "DROP TABLE IF EXISTS $TBL_ANIMAL"
        database?.execSQL(sqlUpdate)
        onCreate(database)
    }

    /**
     * Función que convierte un booleano a entero y servirá para manejar este tipo de
     * datos en SQLite.
     *
     * @return Regresa la equivalencia de un boleano a entero
     */
    private fun booleanToInt(b: Boolean): Int {
        return if (b) 1 else 0
    }

    /**
     * Función que convierte un entero a booleano y servirá para manejar este tipo de
     * datos en SQLite.
     *
     * @return Regresa la equivalencia de un entero a boleano
     */
    private fun intToBoolean(i: Int): Boolean {
        return i == 1
    }

    /**
     * Método que inserta una entidad Animal
     *
     * @param animal Instancia del tipo Animal.
     *
     * @return Regresa el id que se obtuvo al hacer la operación de insert
     *
     * @throws Exception si los datos no cumplen las restricciones de la tabla.
     */
    fun insert(animal: Animal): Long {
        val db = this.writableDatabase
        var result = -1L
        val contentValues = ContentValues().apply {
            put(NOMBRE, animal.nombre)
            put(DESCRIPCION, animal.descripcion)
            put(SEXO, animal.sexo)
            put(IMAGEN, animal.imagen)
            put(IS_ENFERMO, booleanToInt(animal.isEnfermo))
        }

        try {
            result = db.insert(TBL_ANIMAL, null, contentValues)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        db.close()
        return result
    }

    /**
     * Método que obtiene una entidad Animal
     *
     * @param idAnimal Id de la entidad Animal.
     *
     * @return Regresa una entidad del tipo Anima si se encontró en la Base de Datos.
     */
    fun getAnimalById(idAnimal: Int): Animal? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TBL_ANIMAL WHERE $ID = ?"
        val cursor: Cursor?
        var entity: Animal? = null

        try {

            cursor = db.rawQuery(query, arrayOf(idAnimal.toString()))
            if(cursor.count > 0) {
                cursor.moveToFirst();
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(NOMBRE))
                val description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPCION))
                val sexo = cursor.getString(cursor.getColumnIndexOrThrow(SEXO))
                val imagen = cursor.getString(cursor.getColumnIndexOrThrow(IMAGEN))
                val isEnfermo = cursor.getInt(cursor.getColumnIndexOrThrow(IS_ENFERMO))

                entity = Animal(name, description, imagen, intToBoolean(isEnfermo), sexo, id)
            }

            cursor.close()

        } catch (e: Exception) {
            db.close()
        }

        return entity
    }

    /**
     * Método que regresa una colección de animales que están alojados
     * en la base de datos.
     *
     * @return Regresa un arreglo del tipo Animal
     */
    fun getAllAnimales(): ArrayList<Animal> {
        val animalesList = arrayListOf<Animal>()
        val query = "SELECT * FROM $TBL_ANIMAL ORDER by $ID ASC"
        val db = this.readableDatabase
        // El cursor puede ser nulo porque no puede tener información
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: Exception) {
            db.close()
            return animalesList
        }

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(ID))
                val name = getString(getColumnIndexOrThrow(NOMBRE))
                val description = getString(getColumnIndexOrThrow(DESCRIPCION))
                val sexo = getString(getColumnIndexOrThrow(SEXO))
                val imagen = getString(getColumnIndexOrThrow(IMAGEN))
                val isEnfermo = getInt(getColumnIndexOrThrow(IS_ENFERMO))

                val animal = Animal(name, description, imagen, intToBoolean(isEnfermo), sexo, id)
                animalesList.add(animal)
            }
        }

        cursor.close()

        return animalesList
    }

    /**
     * Método que actualiza una entidad Animal
     *
     * @param animal Instancia del tipo Animal.
     *
     * @return Regresa el id de la entidad al hacer la operación de update
     *
     * @throws Exception si los datos no cumplen las restricciones de la tabla.
     */
    fun updateAnimal(animal: Animal): Int {
        val db = this.writableDatabase
        var result = -1
        val contentValues = ContentValues().apply {
            put(NOMBRE, animal.nombre)
            put(DESCRIPCION, animal.descripcion)
            put(IMAGEN, animal.imagen)
            put(SEXO, animal.sexo)
            put(IS_ENFERMO, booleanToInt(animal.isEnfermo))
        }

        try {
            result = db.update(TBL_ANIMAL, contentValues, "id=?", arrayOf("${animal.id}"))
        } catch (e: Exception) {
            db.close()
            return result
        }

        db.close()

        return result
    }

    /**
     * Método que elimina una entidad Animal
     *
     * @param idAnimal Id de la entidad Animal.
     *
     * @return Regresa el id de la entidad al hacer la operación de delete, si es menor a cero no
     *         se encontró la entidad en la Base de Datos.
     */
    fun deleteAnimal(idAnimal: Int): Int {
        val db = this.writableDatabase
        val result = db.delete(TBL_ANIMAL, "id=?", arrayOf("$idAnimal"))
        db.close()

        return result
    }


}
