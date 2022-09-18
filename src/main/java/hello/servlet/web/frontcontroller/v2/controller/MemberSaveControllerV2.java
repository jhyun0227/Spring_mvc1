package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

     @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //비즈니스 로직 수행
         String username = request.getParameter("username");
         int age = Integer.parseInt(request.getParameter("age")); //getParameter로 가져오는 값은 String이기 때문에 변환해주어야한다.

         Member member = new Member(username, age);
         memberRepository.save(member);

         //Model에 데이터를 보관
         request.setAttribute("member", member);

         /*V1의 코드
         //view로 이동
         String viewPath = "/WEB-INF/views/save-result.jsp";
         RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
         dispatcher.forward(request, response);
         */

         //V2 코드
         return new MyView("/WEB-INF/views/save-result.jsp");

     }
}
