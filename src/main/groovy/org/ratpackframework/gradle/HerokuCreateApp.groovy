package org.ratpackframework.gradle

import com.heroku.api.App
import com.heroku.api.Heroku
import com.heroku.api.HerokuAPI

class HerokuCreateApp extends HerokuApp {

    @Override
    void executeOnApp(HerokuAPI herokuAPI, String appName){

        def app
        if(appName){
            logger.quiet "\nUsing name $appName to create Cedar Stack."
            app = herokuAPI.createApp(new App().on(Heroku.Stack.Cedar).named(appName));
        } else {
            logger.quiet "\nUsing Heroku suggested name to create Cedar Stack."
            app = herokuAPI.createApp(new App().on(Heroku.Stack.Cedar))
        }

        logger.quiet "\nApplication $app created!"
    }

}