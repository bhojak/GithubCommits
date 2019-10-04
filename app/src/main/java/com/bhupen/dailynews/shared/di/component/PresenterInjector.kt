package com.bhupen.dailynews.shared.di.component

/**
 * Created by Bhupen
 */
import com.bhupen.dailynews.features.post.PostPresenter
import com.bhupen.dailynews.shared.base.BaseView
import com.bhupen.dailynews.shared.di.module.ContextModule
import com.bhupen.dailynews.shared.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(postPresenter: PostPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}