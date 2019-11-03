package com.br.photosearch;

import com.br.photosearch.domain.dagger.DaggerAppComponent;
import com.br.photosearch.domain.dagger.auth.RetrofitModule;
import com.br.photosearch.domain.dagger.repository.RepositoryModule;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .app(this)
                .retrofitModule(new RetrofitModule(this))
                .repositoryModule(new RepositoryModule()).build();
    }
}
