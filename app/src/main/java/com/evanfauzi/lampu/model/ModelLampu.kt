package com.evanfauzi.lampu.model

class ModelLampu {
    var key: String? = null
    var no: Int? = null
    var nilai: String? = null
    var kondisi: String? = null

    constructor() {}

    constructor(no: Int?, nilai: String?, kondisi: String?) {
        this.no = no
        this.nilai = nilai
        this.kondisi = kondisi
    }
}