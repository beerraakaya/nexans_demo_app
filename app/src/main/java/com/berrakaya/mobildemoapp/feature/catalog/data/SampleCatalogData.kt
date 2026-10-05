package com.berrakaya.mobildemoapp.feature.catalog.data

import com.berrakaya.mobildemoapp.core.model.LocalizedText
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.Product
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.ProductGroup
import com.berrakaya.mobildemoapp.feature.catalog.domain.model.ProductSpec

internal object SampleCatalogData {

    private const val INDUSTRIAL = "industrial"
    private const val GRID = "grid"
    private const val TELECOM = "telecom"
    private const val BUILDING = "building"

    private val VOLTAGE = text("Rated voltage", "Anma gerilimi")
    private val CONDUCTOR = text("Conductor", "İletken")
    private val INSULATION = text("Insulation", "Yalıtım")
    private val SHEATH = text("Sheath", "Dış kılıf")
    private val FIBER_COUNT = text("Fiber count", "Fiber sayısı")
    private val FIBER_TYPE = text("Fiber type", "Fiber tipi")
    private val BANDWIDTH = text("Bandwidth", "Bant genişliği")

    val groups = listOf(
        group(
            INDUSTRIAL,
            text("Industrial Cables", "Endüstriyel Kablolar"),
            text(
                "Flexible and control cables for machinery and automation",
                "Makine ve otomasyon için esnek ve kontrol kabloları",
            ),
        ),
        group(
            GRID,
            text("Power Grid Cables", "Enerji Şebekesi Kabloları"),
            text(
                "Medium voltage cables for distribution networks",
                "Dağıtım şebekeleri için orta gerilim kabloları",
            ),
        ),
        group(
            TELECOM,
            text("Telecom Infrastructure", "Telekom Altyapısı"),
            text(
                "Fiber optic and copper cables for data networks",
                "Veri ağları için fiber optik ve bakır kablolar",
            ),
        ),
        group(
            BUILDING,
            text("Building Wires", "Yapısal Enerji Kabloları"),
            text(
                "Low voltage cables for residential and commercial buildings",
                "Konut ve ticari yapılar için alçak gerilim kabloları",
            ),
        ),
    )

    val products = listOf(
        Product(
            id = "h07rn-f",
            groupId = INDUSTRIAL,
            code = "H07RN-F",
            name = text("Rubber-insulated flexible cable", "Kauçuk yalıtımlı esnek kablo"),
            description = text(
                "Heavy-duty flexible cable for mobile equipment and harsh environments.",
                "Mobil ekipmanlar ve zorlu ortamlar için dayanıklı esnek kablo.",
            ),
            specs = listOf(
                ProductSpec(VOLTAGE, "450/750 V"),
                ProductSpec(CONDUCTOR, "Cu"),
                ProductSpec(INSULATION, "EPR"),
            ),
        ),
        Product(
            id = "liycy",
            groupId = INDUSTRIAL,
            code = "LiYCY",
            name = text("Shielded control cable", "Ekranlı kontrol kablosu"),
            description = text(
                "Copper-braided control cable that protects signals from interference.",
                "Sinyalleri parazitten koruyan bakır örgülü kontrol kablosu.",
            ),
            specs = listOf(
                ProductSpec(VOLTAGE, "250 V"),
                ProductSpec(CONDUCTOR, "Cu"),
                ProductSpec(INSULATION, "PVC"),
            ),
        ),
        Product(
            id = "na2xsf2y",
            groupId = GRID,
            code = "NA2XS(F)2Y",
            name = text("MV XLPE aluminium cable", "OG XLPE alüminyum kablo"),
            description = text(
                "Longitudinally watertight medium voltage cable for underground networks.",
                "Yer altı şebekeleri için boyuna su geçirmez orta gerilim kablosu.",
            ),
            specs = listOf(
                ProductSpec(VOLTAGE, "12/20 kV"),
                ProductSpec(CONDUCTOR, "Al"),
                ProductSpec(INSULATION, "XLPE"),
            ),
        ),
        Product(
            id = "n2xsf2y",
            groupId = GRID,
            code = "N2XS(F)2Y",
            name = text("MV XLPE copper cable", "OG XLPE bakır kablo"),
            description = text(
                "High-capacity copper cable for substations and industrial plants.",
                "Trafo merkezleri ve sanayi tesisleri için yüksek kapasiteli bakır kablo.",
            ),
            specs = listOf(
                ProductSpec(VOLTAGE, "20.3/35 kV"),
                ProductSpec(CONDUCTOR, "Cu"),
                ProductSpec(INSULATION, "XLPE"),
            ),
        ),
        Product(
            id = "a-dqzn2y",
            groupId = TELECOM,
            code = "A-DQ(ZN)2Y",
            name = text("Outdoor fiber optic cable", "Dış ortam fiber optik kablo"),
            description = text(
                "Rodent-protected loose tube cable for duct and direct burial installation.",
                "Kanal ve doğrudan gömme uygulamaları için kemirgen korumalı gevşek tüp kablo.",
            ),
            specs = listOf(
                ProductSpec(FIBER_COUNT, "24"),
                ProductSpec(FIBER_TYPE, "G.652.D"),
                ProductSpec(SHEATH, "PE"),
            ),
        ),
        Product(
            id = "u-utp-cat6",
            groupId = TELECOM,
            code = "U/UTP Cat6",
            name = text("Category 6 data cable", "Kategori 6 veri kablosu"),
            description = text(
                "Structured cabling solution for offices and data centers.",
                "Ofisler ve veri merkezleri için yapısal kablolama çözümü.",
            ),
            specs = listOf(
                ProductSpec(BANDWIDTH, "250 MHz"),
                ProductSpec(CONDUCTOR, "Cu"),
                ProductSpec(SHEATH, "LSZH"),
            ),
        ),
        Product(
            id = "nym-j",
            groupId = BUILDING,
            code = "NYM-J",
            name = text("PVC sheathed installation cable", "PVC kılıflı tesisat kablosu"),
            description = text(
                "Standard cable for fixed indoor installations in dry and damp rooms.",
                "Kuru ve nemli iç mekanlarda sabit tesisat için standart kablo.",
            ),
            specs = listOf(
                ProductSpec(VOLTAGE, "300/500 V"),
                ProductSpec(CONDUCTOR, "Cu"),
                ProductSpec(INSULATION, "PVC"),
            ),
        ),
        Product(
            id = "nhxmh-j",
            groupId = BUILDING,
            code = "NHXMH-J",
            name = text("Halogen-free installation cable", "Halojensiz tesisat kablosu"),
            description = text(
                "Low-smoke cable for public buildings such as hospitals and airports.",
                "Hastane ve havalimanı gibi kamu binaları için az duman yayan kablo.",
            ),
            specs = listOf(
                ProductSpec(VOLTAGE, "300/500 V"),
                ProductSpec(CONDUCTOR, "Cu"),
                ProductSpec(SHEATH, "LSZH"),
            ),
        ),
    )

    private fun group(id: String, name: LocalizedText, description: LocalizedText) =
        ProductGroup(id = id, name = name, description = description, productCount = 0)

    private fun text(en: String, tr: String) = LocalizedText(mapOf("en" to en, "tr" to tr))
}