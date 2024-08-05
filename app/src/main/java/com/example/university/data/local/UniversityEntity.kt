package com.example.university.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "universities")
class UniversityEntity {
    @PrimaryKey
    var name: String=""
    var country: String = "";
    var alpha_two_code: String = "";
    //val web_pages: List<String>
    public fun setName1(name: String) {
        this.name = name
    }
    public fun getName1(): String {
        return this.name;
    }

    public fun setCountry1(name: String) {
        this.country = country
    }
    public fun getCountry1(): String {
        return this.country;
    }

    public fun setCode1(code: String) {
        this.alpha_two_code = code
    }
    public fun getCode1(): String {
        return this.alpha_two_code;
    }
}
