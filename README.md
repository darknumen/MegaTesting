# MegaTesting

Local run of tests":
Set your environment variables

SELENIUM_HEADLESS - true/false
SELENIUM_BROWSER - type of browser for testing
SELENIUM_WAIT - wait time of driver
SELENIUM_URL - url to start

SELENIUM_USERNAME - username for the site to be tested
SELENIUM_PASSWORD - password for the site to be tested

Optional:
CUCUMBER_PUBLISH_ENABLED=true -- to publish test result to the web


For CI/CD:

public:
SELENIUM_HEADLESS - true/false
SELENIUM_BROWSER - type of browser for testing
SELENIUM_WAIT - wait time of driver
SELENIUM_URL - url to start

secret variable:
SELENIUM_USERNAME - username for the site to be tested
SELENIUM_PASSWORD - password for the site to be tested
