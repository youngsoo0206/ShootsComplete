package com.Shoots.controller;

import com.Shoots.domain.BusinessUser;
import com.Shoots.domain.RegularUser;
import com.Shoots.domain.Weather;
import com.Shoots.redis.RedisService;
import com.Shoots.service.weather.WeatherService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final RedisService redisService;
    private WeatherService weatherService;

    @GetMapping("/mainBefore") //로그인이 성공하면 main 주소로 가기 전 로그인 유저 타입을 확인하는 경로
    public void home(@AuthenticationPrincipal Object principal, HttpSession session, HttpServletResponse response) throws IOException {

        if (principal instanceof RegularUser) {
            RegularUser regularUser = (RegularUser) principal;
        } else if (principal instanceof BusinessUser) {
            BusinessUser businessUser = (BusinessUser) principal;
        }

        if ("pending".equals(session.getAttribute("businessAccess"))) {
            response.setContentType("text/html; charset=utf-8");
            response.setCharacterEncoding("utf-8");

            PrintWriter out = response.getWriter();
            out.println("<script type='text/javascript'>");
            out.println("if(confirm('미승인 상태입니다. 관리자의 승인을 기다려주세요.')){");
            out.println("location.href='/Shoots/logout';");
            out.println("} else { location.href='/Shoots/logout'; }");
            out.println("</script>");
            out.flush();
        }
        response.sendRedirect("/Shoots/main");

    }

    @GetMapping(value = "/main")
    public String main(Model model, @RequestParam(defaultValue = "서울특별시") String first,
                       @RequestParam(defaultValue = "강남구") String second,
                       @RequestParam(defaultValue = "역삼1동") String third) throws IOException, ParseException {

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        System.out.println("first: " + first + "second: " + second + "third: " + third);

        Map<String, Integer> weather = redisService.getLocationData(first, second, third);
        System.out.println("weather > x : " + weather.get("nx") + " / y : " + weather.get("ny"));

        Weather weatherData = weatherService.getWeather(today, weather.get("nx"), weather.get("ny"));
        List<Weather> weatherDataForecast = weatherService.getWeatherForecast(today, weather.get("nx"), weather.get("ny"));

        List<Weather> firstSixWeatherData = weatherDataForecast.stream()
                .limit(6)
                .map(forecast -> {
                    forecast.setWindSpeedAsNumber(Double.parseDouble(forecast.getWindSpeed()));
                    return forecast;
                })
                .collect(Collectors.toList());

        System.out.println("firstSixWeatherData  = " + firstSixWeatherData.toString());

        int currentTime = Integer.parseInt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH")));
        System.out.println("Current Time = " + currentTime);

        model.addAttribute("weather", weatherData);
        model.addAttribute("firstSixWeatherData", firstSixWeatherData);
        model.addAttribute("today", today);
        model.addAttribute("currentTime", currentTime);
        model.addAttribute("first", first);
        model.addAttribute("second", second);
        model.addAttribute("third", third);

        return "home/home";
    }

    @GetMapping("/getOptions")
    @ResponseBody
    public Map<String, Map<String, List<String>>> getLocationOptions() throws IOException {
        Map<String, Map<String, List<String>>> options = redisService.getLocationOptions();
        return options;
    }

    @GetMapping("/error/403")
    public String error_403() {
        return "error/403";
    }



}
