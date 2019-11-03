package com.br.photosearch.presentation.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BaseViewModelManager<S : ViewState>(initState: S) {
    var viewState: S = initState
        private set

    abstract class ViewModelState<S : ViewState, E : ViewEvent> : ViewModel() {

        var disposable: CompositeDisposable = CompositeDisposable()

        private val stateManager by lazy {
            BaseViewModelManager(initialState)
        }

        abstract val initialState: S

        fun updateState(block: S.() -> S) {
            stateManager.viewState = stateManager.viewState.block()
            (state as MutableLiveData).postValue(stateManager.viewState)
        }

        fun updateEvent(currentEvent: E) {
            (event as MutableLiveData).postValue(currentEvent)
        }

        val state: LiveData<S> = MutableLiveData()
        val event: LiveData<E> = MutableLiveData()

        override fun onCleared() {
            super.onCleared()
            disposable.clear()
        }

        protected infix operator fun CompositeDisposable.plus(subscription: Disposable): CompositeDisposable {
            this.add(subscription)
            return this
        }
    }
}


interface ViewState
interface ViewEvent