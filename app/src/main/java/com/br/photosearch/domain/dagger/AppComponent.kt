package com.br.photosearch.domain.dagger

import com.br.photosearch.App
import com.br.photosearch.domain.dagger.activities.ActivitiesModule
import com.br.photosearch.domain.dagger.remoterequests.RetrofitModule
import com.br.photosearch.domain.dagger.repository.RepositoryModule
import com.br.photosearch.domain.dagger.viewmodels.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        RetrofitModule::class,
        ActivitiesModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: App): Builder
        fun retrofitModule(retrofitModule: RetrofitModule): Builder
        fun repositoryModule(repository: RepositoryModule): Builder
        fun build(): AppComponent
    }
}