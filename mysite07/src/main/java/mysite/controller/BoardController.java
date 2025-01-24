package mysite.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mysite.security.UserDetailsImpl;
import mysite.service.BoardService;
import mysite.vo.BoardVo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @RequestMapping({"", "/"})
    public String board(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "kwd", defaultValue = "") String kwd) {

        Map<String, Object> data = boardService.getContentsList(page, kwd);
        model.addAttribute("pagingData", data.get("pagingData"));
        model.addAttribute("boardList", data.get("list"));

        return "board/list";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@RequestParam("id") Long id,
                       @RequestParam("currentPage") Integer currentPage,
                       @CookieValue(value = "viewPage", required = false, defaultValue = "") String viewPage,
                       HttpServletResponse response,
                       Model model) {

        response.addCookie(boardService.countView(id, viewPage));
        model.addAttribute("board", boardService.getContents(id));
        model.addAttribute("currentPage", currentPage);
        return "board/view";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String viewWrite(@RequestParam(value = "id", required = false) Long id, Model model) {

        if (id != null) {
            model.addAttribute("board", boardService.getContents(id));
        }
        return "board/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String write(@AuthenticationPrincipal UserDetailsImpl authUser, BoardVo vo) {

        vo.setUser_id(authUser.getId());

        boardService.addContents(vo);
        return "redirect:/board";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String viewModify(@AuthenticationPrincipal UserDetailsImpl authUser, @RequestParam("id") Long id, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {
        BoardVo board = boardService.getContents(id);

        if (!authUser.getId().equals(board.getUser_id())) {
            return "redirect:/board?page=" + currentPage;
        }

        model.addAttribute("board", board);
        model.addAttribute("currentPage", currentPage);
        return "board/modify";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(@AuthenticationPrincipal UserDetailsImpl authUser, BoardVo vo, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {

        vo.setUser_id(authUser.getId());

        boardService.updateContents(vo);
        model.addAttribute("currentPage", currentPage);
        return "redirect:/board/view?id=" + vo.getId() + "&currentPage=" + currentPage;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@AuthenticationPrincipal UserDetailsImpl authUser, @PathVariable("id") Long id, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, Model model) {

        boardService.deleteContents(id, authUser.getId());
        model.addAttribute("currentPage", currentPage);
        return "redirect:/board?page=" + currentPage;
    }
}
