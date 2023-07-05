package bankwithadapter.domain.proxies;

import bankwithadapter.integration.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingProxy implements InvocationHandler {
    private Object target;
    Logger logger;

    public LoggingProxy(Object target) {
        this.target = target;
        logger = new Logger();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnValue = method.invoke(target, args);
        logger.log("Calling method " + method.getDeclaringClass() + "." + method.getName() + " with arguments: ");
        if (args != null)
            for(int argumentPosition = 0; argumentPosition < args.length; argumentPosition++)
                logger.log(" - param["+ argumentPosition +"]: " +args[argumentPosition].toString());
        return returnValue;
    }
}
