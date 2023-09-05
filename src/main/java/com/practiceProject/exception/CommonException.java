package com.practiceProject.exception;

import lombok.Getter;

/*
-1 : DBException - msg + " 처리하는데 이상이 생겼습니다."
-2 : NoMatchesException - msg + " 일치하지 읺습니다."
-3 : DuplicateException - msg + " 중복되었습니다."
-4 : NotEnoughException - msg + " 부족합니다."
-5 : NotRegisterException - msg + " 등록해주세요."
-6 : NoAccessUserException - 접근 할 수 없는 사용자입니다.
-7 : FailException - msg + " 관련된 데이터가 있어 실패했습니다."
-8 : NoneException - msg + " 없습니다."
-9 : ExistException - msg + " 이미 존재합니다."
-10 : ResignException - 이미 탈퇴된 사용자입니다.
 */
@Getter
public class CommonException extends RuntimeException {
    protected String msg;
    protected String code;


}
