package alex.orobynskyi.niprices.domain.roomDb;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

import alex.orobynskyi.niprices.utils.AppUtils;

public class GameDocConverter {
    @TypeConverter
    @SuppressWarnings("WeakerAccess") //Type converters needs to be Public
    public static List<String> storedStringToMyObjects(String data) {
        return Arrays.asList(data.split("\\s*,\\s*"));
    }

    @TypeConverter
    @SuppressWarnings("WeakerAccess")
    public static String myObjectsToStoredString(List<String> docs) {
        return AppUtils.Companion.join(",", docs);
    }
}