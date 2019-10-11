package alex.orobynskyi.niprices.domain.models.games

import alex.orobynskyi.niprices.domain.roomDb.GameDocConverter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
@TypeConverters(GameDocConverter::class)
data class GameDoc(
    @ColumnInfo(name="version") val _version_: Long?,
    @ColumnInfo(name="age_rating_sorting_i") val age_rating_sorting_i: Int?,
    @ColumnInfo(name="age_rating_type") val age_rating_type: String?,
    @ColumnInfo(name="age_rating_value") val age_rating_value: String?,
    @ColumnInfo(name="change_date") val change_date: String?,
    @ColumnInfo(name="cloud_saves_b") val cloud_saves_b: Boolean?,
    @ColumnInfo(name="club_nintendo") val club_nintendo: Boolean?,
    @ColumnInfo(name="compatible_controller") val compatible_controller: MutableList<String>?,
    @ColumnInfo(name="copyright_s") val copyright_s: String?,
    @ColumnInfo(name="date_from") val date_from: String?,
    @ColumnInfo(name="dates_released_dts") val dates_released_dts: MutableList<String>?,
    @ColumnInfo(name="digital_version_b") val digital_version_b: Boolean?,
    @ColumnInfo(name="excerpt") val excerpt: String?,
    @ColumnInfo(name="fs_id") @PrimaryKey val fs_id: String,
    @ColumnInfo(name="game_categories_txt") val game_categories_txt: MutableList<String>?,
    @ColumnInfo(name="game_category") val game_category: MutableList<String>?,
    @ColumnInfo(name="gift_finder_carousel_image_url_s") val gift_finder_carousel_image_url_s: String?,
    @ColumnInfo(name="gift_finder_detail_page_image_url_s") val gift_finder_detail_page_image_url_s: String?,
    @ColumnInfo(name="gift_finder_wishlist_image_url_s") val gift_finder_wishlist_image_url_s: String?,
    @ColumnInfo(name="hits_i") val hits_i: Int?,
    @ColumnInfo(name="image_url") val image_url: String?,
    @ColumnInfo(name="image_url_h2x1_s") val image_url_h2x1_s: String?,
    @ColumnInfo(name="image_url_sq_s") val image_url_sq_s: String?,
    @ColumnInfo(name="language_availability") val language_availability: MutableList<String>?,
    @ColumnInfo(name="nsuid_txt") val nsuid_txt: MutableList<String>?,
    @ColumnInfo(name="originally_for_t") val originally_for_t: String?,
    @ColumnInfo(name="paid_subscription_required_b") val paid_subscription_required_b: Boolean?,
    @ColumnInfo(name="pg_s") val pg_s: String?,
    @ColumnInfo(name="physical_version_b") val physical_version_b: Boolean?,
    @ColumnInfo(name="play_mode_handheld_mode_b") val play_mode_handheld_mode_b: Boolean?,
    @ColumnInfo(name="play_mode_tabletop_mode_b") val play_mode_tabletop_mode_b: Boolean?,
    @ColumnInfo(name="play_mode_tv_mode_b") val play_mode_tv_mode_b: Boolean?,
    @ColumnInfo(name="playable_on_txt") val playable_on_txt: MutableList<String>?,
    @ColumnInfo(name="players_from") val players_from: Int?,
    @ColumnInfo(name="players_to") val players_to: Int?,
    @ColumnInfo(name="pretty_agerating_s") val pretty_agerating_s: String?,
    @ColumnInfo(name="pretty_date_s") val pretty_date_s: String?,
    @ColumnInfo(name="pretty_game_categories_txt") val pretty_game_categories_txt: MutableList<String>?,
    @ColumnInfo(name="price_discount_percentage_f") val price_discount_percentage_f: Double?,
    @ColumnInfo(name="price_has_discount_b") val price_has_discount_b: Boolean?,
    @ColumnInfo(name="price_lowest_f") val price_lowest_f: Double?,
    @ColumnInfo(name="price_sorting_f") val price_sorting_f: Double?,
    @ColumnInfo(name="product_code_ss") val product_code_ss: MutableList<String>?,
    @ColumnInfo(name="product_code_txt") val product_code_txt: MutableList<String>?,
    @ColumnInfo(name="publisher") val publisher: String?,
    @ColumnInfo(name="sorting_title") val sorting_title: String?,
    @ColumnInfo(name="switch_game_voucher_b") val switch_game_voucher_b: Boolean?,
    @ColumnInfo(name="system_names_txt") val system_names_txt: MutableList<String>?,
    @ColumnInfo(name="system_type") val system_type: MutableList<String>?,
    @ColumnInfo(name="title") val title: String?,
    @ColumnInfo(name="vetitle_extras_txtrsion") val title_extras_txt: MutableList<String>?,
    @ColumnInfo(name="type") val type: String?,
    @ColumnInfo(name="url") val url: String?,
    @ColumnInfo(name="voice_chat_b") val voice_chat_b: Boolean?,
    @ColumnInfo(name="wishlist_email_banner460w_image_url_s") val wishlist_email_banner460w_image_url_s: String?,
    @ColumnInfo(name="wishlist_email_banner640w_image_url_s") val wishlist_email_banner640w_image_url_s: String?,
    @ColumnInfo(name="wishlist_email_square_image_url_s") val wishlist_email_square_image_url_s: String?
) {
    override fun toString(): String {
        return "Doc(_version_=$_version_, age_rating_sorting_i=$age_rating_sorting_i, age_rating_type='$age_rating_type', age_rating_value='$age_rating_value', change_date='$change_date', cloud_saves_b=$cloud_saves_b, club_nintendo=$club_nintendo, compatible_controller=$compatible_controller, copyright_s='$copyright_s', date_from='$date_from', dates_released_dts=$dates_released_dts, digital_version_b=$digital_version_b, excerpt='$excerpt', fs_id='$fs_id', game_categories_txt=$game_categories_txt, game_category=$game_category, gift_finder_carousel_image_url_s='$gift_finder_carousel_image_url_s', gift_finder_detail_page_image_url_s='$gift_finder_detail_page_image_url_s', gift_finder_wishlist_image_url_s='$gift_finder_wishlist_image_url_s', hits_i=$hits_i, image_url='$image_url', image_url_h2x1_s='$image_url_h2x1_s', image_url_sq_s='$image_url_sq_s', language_availability=$language_availability, nsuid_txt=$nsuid_txt, originally_for_t='$originally_for_t', paid_subscription_required_b=$paid_subscription_required_b, pg_s='$pg_s', physical_version_b=$physical_version_b, play_mode_handheld_mode_b=$play_mode_handheld_mode_b, play_mode_tabletop_mode_b=$play_mode_tabletop_mode_b, play_mode_tv_mode_b=$play_mode_tv_mode_b, playable_on_txt=$playable_on_txt, players_from=$players_from, players_to=$players_to, pretty_agerating_s='$pretty_agerating_s', pretty_date_s='$pretty_date_s', pretty_game_categories_txt=$pretty_game_categories_txt, price_discount_percentage_f=$price_discount_percentage_f, price_has_discount_b=$price_has_discount_b, price_lowest_f=$price_lowest_f, price_sorting_f=$price_sorting_f, product_code_ss=$product_code_ss, product_code_txt=$product_code_txt, publisher='$publisher', sorting_title='$sorting_title', switch_game_voucher_b=$switch_game_voucher_b, system_names_txt=$system_names_txt, system_type=$system_type, title='$title', title_extras_txt=$title_extras_txt, type='$type', url='$url', voice_chat_b=$voice_chat_b, wishlist_email_banner460w_image_url_s='$wishlist_email_banner460w_image_url_s', wishlist_email_banner640w_image_url_s='$wishlist_email_banner640w_image_url_s', wishlist_email_square_image_url_s='$wishlist_email_square_image_url_s')"
    }
}