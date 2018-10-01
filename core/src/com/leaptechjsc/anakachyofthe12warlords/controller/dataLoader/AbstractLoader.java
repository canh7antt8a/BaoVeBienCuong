package com.leaptechjsc.anakachyofthe12warlords.controller.dataLoader;

import com.badlogic.gdx.assets.AssetManager;

public abstract class AbstractLoader {
	protected AssetManager assetManager;

	public AbstractLoader() {
		this.assetManager = new AssetManager();
	}
	
	public abstract void postProcess();

	public boolean update() {
		return assetManager.update();
	}

	public float getProgress() {
		return assetManager.getProgress();
	}

	public void dispose() {
		assetManager.dispose();
	}
}
