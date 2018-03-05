# SingleActivity

Gradle
```
compile 'net.mieczkowski:singleactivity:1.0.0'
```


//TODO: Better readme

Controllers
1) Back button = Extend BackController	
2) Close button = Extend CloseController
3) Menu button = Extend MenuController
4) Doesn't mess with toolbar = Extend BaseChildController
5) Parallax controller = Extend Back/Close/Menu/BaseController, implements ICollapse
6) Tabs layot = Extend Back/Close/Menu/BaseController, implements ITabs
7) Individual tabs = Extend BaseChildController, implements ITab
8) Popups - Extebd BaseDialogController

How to show controllers:
Simply call .show()
First controller (root) has to be set by:
```
if (!router.hasRootController()) {
	router.setRoot(RouterTransaction.with(BaseController()))
}
```


How to pop controllers:
Simply call router.popCurrentController() //popToTag()/popToRoot() (Tag is set to default javaClass.name but can be set on the fly)
or let the back button do all the work for you


How to get a parallax controller:
1) With your controller, implement ICollapse
2) Pass back a controller that extends BaseCollapseController (this controller allows you to put any custom view into the toolbar and have it be collapsable)


How to get a tab controller:
1) Make sure you have a viewPager on your layout for this controller
2) With your controller, implement ITabs
3) Create an adapter that extends BasePageAdapter (Controllers used by BasePageAdapter must implement ITab. It is recommended for these tabs to extend BaseChildController)


Animations
All controllers use ControllerChangeHandler for their pop/push. 
Extending AnimatorChangeHandler will give freedom for custom animations (see HorizontalChangeHandler and VerticalChangeHandler for examples)


