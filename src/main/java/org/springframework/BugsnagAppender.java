package org.springframework;

import com.bugsnag.Bugsnag;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

public class BugsnagAppender extends ConsoleAppender {

    private final Bugsnag bugsnag = new Bugsnag("api-key");

    public BugsnagAppender() {
        bugsnag.setEndpoint("http://localhost:8000");
    }

    @Override
    public void append(LoggingEvent event) {
        super.append(event);
        ThrowableInformation info = event.getThrowableInformation();

        if (info != null && info.getThrowable() != null) {
            bugsnag.notify(info.getThrowable());
        }
    }

}
