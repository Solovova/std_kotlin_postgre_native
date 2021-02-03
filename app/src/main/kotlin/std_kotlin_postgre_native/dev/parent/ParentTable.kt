package std_kotlin_postgre_native.dev.parent

import std_kotlin_postgre_native.dev.parent.field.FieldAnnotation

open class ParentTable {
    open val fields: Map<String,FieldAnnotation> = mapOf()
    open var pTableName: String = ""

    open fun getTableName():String {
        return this.pTableName
    }

    private fun getSqlCreateTable(){

    }

    private fun getSqlDropTable(){

    }
}