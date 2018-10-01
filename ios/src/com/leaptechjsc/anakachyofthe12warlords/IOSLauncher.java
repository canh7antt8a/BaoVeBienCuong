package com.leaptechjsc.anakachyofthe12warlords;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.leaptechjsc.anakachyofthe12warlords.BaoVeBienCuong;

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new BaoVeBienCuong(new IRequestHandler() {
            @Override
            public void showToast(String text) {

            }

            @Override
            public void sendHighScore(int score) {

            }

            @Override
            public void shareGetCoin() {

            }

            @Override
            public void shareGetStar() {

            }

            @Override
            public void exit() {

            }

            @Override
            public void setCoin(int coin) {

            }

            @Override
            public int getCoin() {
                return 0;
            }

            @Override
            public void setStar(int star) {

            }

            @Override
            public int getStar() {
                return 0;
            }

            @Override
            public void showSmallAd() {

            }

            @Override
            public void showLargeAd() {

            }

            @Override
            public void hideAd() {

            }

            @Override
            public void showADVideo() {

            }
        }), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}