import android.content.Context
import com.example.habittracker.database.HabitTrackerDatabase

val DB_NAME = "habittrakerdb"
fun buildDb(context: Context): HabitTrackerDatabase {
    val db = HabitTrackerDatabase.buildDatabase(context)
    return db
}