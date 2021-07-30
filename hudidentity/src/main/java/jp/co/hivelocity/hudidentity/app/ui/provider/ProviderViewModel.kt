package jp.co.hivelocity.hudidentity.app.ui.provider

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jp.co.hivelocity.hudidentity.model.provider.Provider
import jp.co.hivelocity.hudidentity.source.ProviderDataSource

class ProviderViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var dataObserver = MutableLiveData<List<Provider>>()
    var exceptionObserver = MutableLiveData<String>()
    var loadingObserver: MutableLiveData<Boolean> = MutableLiveData()

    //private val providerDataSource = Injection.provideProviderRepository()

    fun getProviders(providerDataSource: ProviderDataSource) {
        loadingObserver.value = true
        compositeDisposable.add(
            providerDataSource.getProviders()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ apiResponse ->
                    loadingObserver.value = false
                    dataObserver.value = apiResponse
                }, { e ->
                    e.printStackTrace()
                    loadingObserver.value = false
                    exceptionObserver.value = e.localizedMessage
                })
        )
    }
}