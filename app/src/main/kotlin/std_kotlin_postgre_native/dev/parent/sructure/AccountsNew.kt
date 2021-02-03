package std_kotlin_postgre_native.dev.parent.sructure

import std_kotlin_postgre_native.dev.parent.ParentTable
import std_kotlin_postgre_native.dev.parent.field.FieldAnnotation
import std_kotlin_postgre_native.dev.parent.field.FieldType

class AccountsNew : ParentTable() {
    override val fields = mapOf(
        "id" to FieldAnnotation(
            FieldType.Integer,
            len = 0,
            index = true,
            indNumerator = "accounts_id_sec",
            unicue = true
        )
    )

    override var pTableName: String = "accounts"
}