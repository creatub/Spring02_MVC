/**
 * 
 */
  function check() {
    if (!isKor(mf.name)) {
      alert('이름은 한글만 가능합니다');
      mf.name.select();
      return;
    }
    //*아이디는 영문자로 시작하고, 영문자, 숫자, _, !, #만 4~8자 이내
    if (!isUserid(mf.userid)) {
      alert(
        '아이디는 영문자로 시작하고, 영문자, 숫자, _, !, #만 4~8자 이내여야 해요'
      );
      mf.userid.select();
      return;
    }
    mf.submit();
    if (!isPassword(mf.pwd)) {
      alert('비밀번호는 영문자,숫자,.!_으로만 4~8자 이내여야 해요');
      mf.pwd.select();
      return;
    }
    if (mf.pwd.value != mf.pwd2.value) {
      alert('비밀번호와 비밀번호 확인 값이 달라요');
      mf.pwd2.select();
      return;
    }
    if (!isMobile(mf.hp1, mf.hp2, mf.hp3)) {
      alert('연락처 형식에 맞지 않아요');
      mf.hp2.select();
      return;
    }
  } //------------------
  /*
    ^: 시작을 의미
    $: 끝을 의미
    가-힣: 한글문자여야 함
    +: 1개 이상 나옴
    \s: 공백문자를 허용함
  */
  function isKor(input) {
    let str = input.value;
    let pattern = /^[가-힣\s]+$/;
    let b = pattern.test(str);
    // alert(b);
    return b;
  }
  //flag 문자: i (ignore case), g (global), m(multiline)
  function isUserid(input) {
    // alert(input.value);
    let str = input.value;
    //let pattern = new RegExp(/multi/i)
    let pattern = /^([A-Za-z]){1}[A-Za-z0-9_!#]{3,7}$/;
    let b = pattern.test(str);
    // alert('b: ' + b);
    return b;
  } //---------------------------
  // \w: 알파벳(대소문자), 숫자를 허용한다는 의미
  // {4,8} : 4~8자 이내여야 한다는 의미
  // aB!_.12#, aB!_.12
  function isPassword(input) {
    let str = input.value;
    let pattern = /^[\w!_.]{4,8}$/;
    let b = pattern.test(str);
    // alert(b);
    return b;
  }
  /*
    \b:단어의 경계를 나타내며 해당 패턴이 정확하게 일치해야 함을 의미
    \d{3,4}: 숫자가 3~4자리가 옴을 의미
  */
  function isMobile(input1, input2, input3) {
    let str = input1.value + '-' + input2.value + '-' + input3.value;
    // alert(str);
    let pattern = /\b(010|011|017)[-]\d{3,4}[-]\d{4}\b/;
    let b = pattern.test(str);
    // alert(b);
    return b;
  }