package ru.agladkov.podlodka_compose_basics_demo.old_android.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel : ViewModel() {

    private val _items: MutableLiveData<List<BaseDetailModel>> = MutableLiveData(emptyList())
    val items: LiveData<List<BaseDetailModel>> = _items

    fun fetchDetailInfo(masterTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)

            val newItems = ArrayList<BaseDetailModel>().apply {
                add(HeaderDetailModel(title = masterTitle))
                add(ImageDetailModel(imageUrl = "Test"))
                add(DescriptionDetailModel(description = "Lorem ipsum dolor sit amet, consectetur" +
                        " adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna " +
                        "aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
                        "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                        "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint " +
                        "occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit " +
                        "anim id est laborum"))

                add(CharDetailModel(title = "Char 1"))
                add(CharDetailModel(title = "Char 2"))
                add(CharDetailModel(title = "Char 3"))
                add(CharDetailModel(title = "Char 4"))
                add(CharDetailModel(title = "Char 5"))
                add(CharDetailModel(title = "Char 6"))
                add(CharDetailModel(title = "Char 7"))
                add(CharDetailModel(title = "Char 8"))
                add(CharDetailModel(title = "Char 9"))
                add(CharDetailModel(title = "Char 10"))
            }

            withContext(Dispatchers.Main) {
                _items.postValue(newItems)
            }
        }
    }
}