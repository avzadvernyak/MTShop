package kampukter.mtshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kampukter.mtshop.data.Item
import kampukter.mtshop.data.repository.ItemRepository

class ItemSearchViewModel : ViewModel() {

    private val categoryItems = MutableLiveData<Int>()
    val items: LiveData<List<Item>> =
        Transformations.switchMap(categoryItems) { category -> ItemRepository.getItem(category) }

    fun getCategory(category: Int){
        categoryItems.postValue( category )
    }
/*
    fun getItem(categoryItems: Int): LiveData<List<Item>> {
        return ItemRepository.getItem(categoryItems)
    }
    */

}