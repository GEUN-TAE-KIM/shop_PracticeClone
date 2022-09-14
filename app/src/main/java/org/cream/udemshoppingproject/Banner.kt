package org.cream.udemshoppingproject

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("background_image_url") val backfroundImageUrl: String,
    val badge: BannerBadage,
    val label: String,
    @SerializedName("product_detail") val productDetail: ProductDetail
)

data class BannerBadage(
    val label: String,
    @SerializedName("background_color") val backgroundColor: String
)

data class ProductDetail(
    @SerializedName("brand_name") val brandName: String,
    val label: String,
    @SerializedName("discount_rate") val discountRate: Int,
    val price: Int,
    @SerializedName("thumbnail_image_url") val thumbnail_image_url: String,
    @SerializedName("product_id") val productId: String
)