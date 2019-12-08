package com.uty.databasekoneksi5170411110

class ApiEndPoint {
    companion object{

        //alamat ip didapatkan melalui cmd dengan mengetik ipconfig

        private val SERVER   = "http://192.168.0.107/universitaskotlin/"
        val CREATE = SERVER+"create_fakultas.php"
        val READ = SERVER+"read_fakultas.php"
        val UPDATE = SERVER+"update_fakultas.php"
        val DELETE = SERVER+"delete_fakultas.php"
    }
}