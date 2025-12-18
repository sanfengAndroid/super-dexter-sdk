package io.github.sanfengandroid.sdexter.testapp;

import io.github.sanfengandroid.sdexter.sdk.BaseConfig;
import io.github.sanfengandroid.sdexter.sdk.BuildConfig;
import io.github.sanfengandroid.sdexter.sdk.GlobalConfig;

@GlobalConfig(removePackages = "com.abcd", version = BuildConfig.VERSION_NAME)
public class GlobalMergeConfig extends BaseConfig {
}
