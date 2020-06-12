package cs544.exercise13_1.aop;

import cs544.exercise13_1.EmailSender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Aspect
@Component
public class LogAspect {

    @After("@annotation(LogAOP)")
    public void logExecutionTime(JoinPoint joinPoint) throws Throwable {
        System.out.println(LocalDateTime.now() + " method= sendMail");
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0){
            System.out.print(" address=" + args[0]);
        }
        if (args != null && args.length > 1){
            System.out.print(" message=" + args[1]);
        }
        Object target = joinPoint.getTarget();
        if (target instanceof EmailSender){
            String outgoingMailServer = ((EmailSender) target).getOutgoingMailServer();
            System.out.println("Outgoing mail server = "+ outgoingMailServer);
        }
    }
}
