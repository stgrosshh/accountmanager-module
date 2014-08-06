// This is a test harness for your module
// You should do something interesting in this harness
// to test out the module and to provide instructions
// to users on how to use it by example.


// open a single window
var win = Ti.UI.createWindow({
	backgroundColor:'white'
});
var label = Ti.UI.createLabel();
win.add(label);
win.open();


// Listen for change events.
aSlider.addEventListener('change', function(e) {
	Ti.API.info('Slider value: ' + Math.round(e.value) + ' (actual: ' + Math.round(aSlider.value) + ')');
});

// Add to the parent view.
parentView.add(aSlider);
aSlider.show();

// TODO: write your module tests here
var accman2 = require('org.bcbhh');
Ti.API.info("module is => " + accman2);
accman2.getAccounts()


