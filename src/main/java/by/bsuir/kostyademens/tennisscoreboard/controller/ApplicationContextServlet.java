package by.bsuir.kostyademens.tennisscoreboard.controller;

import by.bsuir.kostyademens.tennisscoreboard.service.NewMatchService;
import by.bsuir.kostyademens.tennisscoreboard.service.OnGoingMatchesService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextServlet implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        context.setAttribute("newMatchService", new NewMatchService());
        context.setAttribute("onGoingMatchesService", new OnGoingMatchesService());
    }
}
