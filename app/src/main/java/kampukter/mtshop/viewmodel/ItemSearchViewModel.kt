package kampukter.mtshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kampukter.mtshop.data.Item
import kampukter.mtshop.data.repository.ItemRepository

class ItemSearchViewModel : ViewModel() {

    fun getItem(): LiveData<List<Item>> {
        return ItemRepository.getItem()
    }

}