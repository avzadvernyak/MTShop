package kampukter.mtshop.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ItemsPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    private val pageName = listOf("Тонометры","Для здоровья","Расходники")

    override fun getItem(position: Int): Fragment {
        return ItemListFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageName[position]
    }
}