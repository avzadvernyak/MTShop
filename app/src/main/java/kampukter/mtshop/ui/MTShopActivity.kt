package kampukter.mtshop.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kampukter.mtshop.R
import kotlinx.android.synthetic.main.mtshop_list_activity.*

class MTShopActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mtshop_list_activity)
        setSupportActionBar(toolbar)

        viewPager.adapter = ItemsPagerAdapter(supportFragmentManager)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mtshop_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId){
            R.id.action_repair_service ->{
                Snackbar.make(toolbar, "Repair Service", Snackbar.LENGTH_LONG).show()
                true
            }
            R.id.action_contacts_view -> {
                Snackbar.make(toolbar, "покажем контакты", Snackbar.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}