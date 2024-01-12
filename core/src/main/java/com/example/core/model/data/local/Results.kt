package com.example.core.model.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Results(
    @PrimaryKey @SerialName(value = "id") var id: String = "0",
    @SerialName(value = "site_id") var siteId: String? = null,
    @SerialName(value = "title") var title: String = "",
    @SerialName(value = "price") var price: Double = 0.0,
    @SerialName(value = "sale_price") var salePrice: String? = null,
    @SerialName(value = "currency_id") var currencyId: String? = null,
    @SerialName(value = "available_quantity") var availableQuantity: Int? = null,
    @SerialName(value = "sold_quantity") var soldQuantity: Int? = null,
    @SerialName(value = "buying_mode") var buyingMode: String? = null,
    @SerialName(value = "listing_type_id") var listingTypeId: String? = null,
    @SerialName(value = "stop_time") var stopTime: String? = null,
    @SerialName(value = "condition") var condition: String? = null,
    @SerialName(value = "permalink") var permalink: String = "",
    @SerialName(value = "thumbnail") var thumbnail: String = "",
    @SerialName(value = "thumbnail_id") var thumbnailId: String? = null,
    @SerialName(value = "accepts_mercadopago") var acceptsMercadopago: Boolean? = null,
    @SerialName(value = "original_price") var originalPrice: String? = null,
    @SerialName(value = "category_id") var categoryId: String? = null,
    @SerialName(value = "official_store_id") var officialStoreId: Int? = null,
    @SerialName(value = "domain_id") var domainId: String? = null,
    @SerialName(value = "catalog_product_id") var catalogProductId: String? = null,
    @SerialName(value = "catalog_listing") var catalogListing: Boolean? = null,
    @SerialName(value = "use_thumbnail_id") var useThumbnailId: Boolean? = null,
    @SerialName(value = "offer_score") var offerScore: String? = null,
    @SerialName(value = "offer_share") var offerShare: String? = null,
    @SerialName(value = "match_score") var matchScore: String? = null,
    @SerialName(value = "winner_item_id") var winnerItemId: String? = null,
    @SerialName(value = "melicoin") var melicoin: String? = null,
    @SerialName(value = "discounts") var discounts: String? = null,
    @SerialName(value = "order_backend") var orderBackend: Int? = null,
)
