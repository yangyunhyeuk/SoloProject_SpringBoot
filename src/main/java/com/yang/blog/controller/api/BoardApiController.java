package com.yang.blog.controller.api;

import com.yang.blog.config.auth.PrincipalDetail;
import com.yang.blog.controller.dto.ResponseDto;
import com.yang.blog.controller.service.BoardService;
import com.yang.blog.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        System.out.println("BoardApiController, save 호출됨");
        boardService.글쓰기(board, principal.getUser()); // title, content만 들고 가면 안되기에 user의 정보까지 들고 이동해야 한다.
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> delete(@PathVariable int id) {
        System.out.println("BoardApiController, delete 호출됨");
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        System.out.println("BoardApiController, update 호출됨");
        boardService.글수정하기(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
