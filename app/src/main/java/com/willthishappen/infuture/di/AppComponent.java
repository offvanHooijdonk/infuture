package com.willthishappen.infuture.di;

import com.willthishappen.infuture.app.InFutureApplication;
import com.willthishappen.infuture.di.repository.RepositoryModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        BuildersModule.class,
        RepositoryModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(InFutureApplication application);

        AppComponent build();
    }

    void inject(InFutureApplication app);
}
