package ru.agladkov.podlodka_compose_basics_demo.old_android.master

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Parcelize
data class MasterData(
    val masterId: Int,
    val masterTitle: String
): Parcelable

class MasterViewModel : ViewModel() {

    private val _masterList: MutableLiveData<List<MasterData>> = MutableLiveData(mutableListOf())
    val masterList: LiveData<List<MasterData>> = _masterList

    fun fetchMasterData() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            val dataList = mutableListOf<MasterData>()
            for (i in 0..20) {
                dataList.add(MasterData(masterId = i, masterTitle = "Master $i"))
            }

            withContext(Dispatchers.Main) {
                _masterList.postValue(dataList)
            }
        }
    }
}