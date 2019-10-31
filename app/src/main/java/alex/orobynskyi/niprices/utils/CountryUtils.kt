package alex.orobynskyi.niprices.utils

object CountryUtils {
    val asia: List<String> = "AE AZ HK IN JP KR MY SA SG TR TW".split(" ")
    val europe: List<String> = ("AD AL AT AU BA BE BG BW CH CY CZ DE DJ DK EE ER ES FI FR GB GG GI GR HR HU IE IM IS IT JE LI LS LT LU " +
            "LV MC ME MK ML MR MT MZ NA NE NL NO NZ PL PT RO RS RU SD SE SI SK SM SO SZ TD VA ZA ZM ZW").split(" ")
    val americas: List<String> = "AG AI AR AW BB BM BO BR BS BZ CA CL CO CR DM DO EC GD GF GP GT GY HN HT JM KN KY LC MQ MS MX NI PA PE PY SR SV TC TT US UY VC VE VG VI".split(" ")
    val countries: List<String> = "AT AU BE BG CA CH CY CZ DE DK EE ES FI FR GB GR HR HU IE IT JP LT LU LV MT MX NL NO NZ PL PT RO RU SE SI SK US ZA".split(" ")
    val firstNSUID = 70_010_000_000_000
}