package com.evanfauzi.lampu.model

class ModelLampu {
    var key: String? = null
    var tanggal: String? = null
    var namalampu: String? = null
    var kondisi: String? = null
    var nilai: Int? = null
    var cek: Int? = null

    constructor() {}
    constructor(tanggal: String?, namalampu: String?, kondisi: String?, nilai: Int?, cek: Int?) {
        this.tanggal = tanggal
        this.namalampu = namalampu
        this.kondisi = kondisi
        this.nilai = nilai
        this.cek = cek
    }

}