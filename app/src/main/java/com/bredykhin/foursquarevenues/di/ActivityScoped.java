package com.bredykhin.foursquarevenues.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Since {@link AppComponent} is declared as ({@code @Singleton}, we need to satisfy Dagger
 * requirement for sub components to also have a scope.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
