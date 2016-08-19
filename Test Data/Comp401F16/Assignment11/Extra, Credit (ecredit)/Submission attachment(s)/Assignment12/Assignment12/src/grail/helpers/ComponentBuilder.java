package grail.helpers;

import java.awt.Color;
import grail.draw.interfaces.Rectangle;
import grail.draw.objects.BasicRectangle;
import grail.factories.SingletonsCreator;
import grail.paint.AObservablePainter;
import grail.paint.ObservablePainter;
import grail.scene.BridgeScene;
import grail.view.ABackgroundView;
import grail.view.AGorgeView;
import grail.view.APlatformView;
import grail.view.AnAvatarView;
import grail.view.AvatarView;
import grail.view.BackgroundView;
import grail.view.GorgeView;
import grail.view.PlatformView;

public class ComponentBuilder {
	
	public static ObservablePainter buildObservablePainterFromScene(BridgeScene scene, Color backgroundcolor) {
		final int frameWidth = 1600;
		final int frameHeight = 1000;
		
		ObservablePainter singleComponentModel = new AObservablePainter();
		
		Rectangle backgroundRectangle = new BasicRectangle();
		backgroundRectangle.setHeight(frameHeight);
		backgroundRectangle.setWidth(frameWidth);
		backgroundRectangle.setColor(backgroundcolor);
		
		//create views to go in ObservablePainter
		BackgroundView background = new ABackgroundView(backgroundRectangle, singleComponentModel);
		AvatarView arthurView = new AnAvatarView(scene.getArthur(), singleComponentModel);
		AvatarView galahadView = new AnAvatarView(scene.getGalahad(), singleComponentModel);
		AvatarView robinView = new AnAvatarView(scene.getRobin(), singleComponentModel);
		AvatarView lancelotView = new AnAvatarView(scene.getLancelot(), singleComponentModel);
		AvatarView guardView = new AnAvatarView(scene.getGuard(), singleComponentModel);
		PlatformView knightPlatformView = new APlatformView(scene.getKnightArea(), singleComponentModel);
		PlatformView guardPlatformView = new APlatformView(scene.getGuardArea(), singleComponentModel);
		GorgeView gorgeView = new AGorgeView(scene.getGorge(), singleComponentModel);
		
		//Add views to ObservablePainter
		
		singleComponentModel.addPaintListener(background);
		singleComponentModel.addPaintListener(knightPlatformView);
		singleComponentModel.addPaintListener(guardPlatformView);
		singleComponentModel.addPaintListener(gorgeView);
		singleComponentModel.addPaintListener(arthurView);
		singleComponentModel.addPaintListener(galahadView);
		singleComponentModel.addPaintListener(robinView);
		singleComponentModel.addPaintListener(lancelotView);
		singleComponentModel.addPaintListener(guardView);

		
		return singleComponentModel;
	}
	
	public static ObservablePainter buildObservablePainterFromScene(BridgeScene scene) {
		return buildObservablePainterFromScene(scene, Color.WHITE);
	} 
	
	public static ObservablePainter buildObservablePainterFromScene() {
		return buildObservablePainterFromScene(SingletonsCreator.bridgeSceneFactoryMethod());
	}

}
