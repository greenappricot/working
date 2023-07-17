package com.bs.spring.common;

public class PageFactory {
	
	// pageBar를 만들어서 반환해주는 메소드를 공통으로 사용하기 위해 static으로 선언함
	public static String getPage(int cPage, int numPerpage, int totalData, String url) {
		
		// 매개변수 값을 이용해서 pageBar 만들어주는 함수 (ajax로 만들면 다른 메소드를 사용한다.)
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize = 5;
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		StringBuffer pageBar = new StringBuffer();
		pageBar.append("<ul class='pagination justify-content-center paginateion-sm'>");
		if(pageNo==1) {
			pageBar.append("<li class='page-item disabled'>");
			pageBar.append("<a class='page-link' href='#'>이전");
			pageBar.append("</a>");
			pageBar.append("</li>");
		}else {
			pageBar.append("<li class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:fn_paging(" + (pageNo-1) + ")'>이전");
			pageBar.append("</a>");
			pageBar.append("</li>");
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar.append("<li class='page-item disabled'>");
				pageBar.append("<a class='page-link' href='#'>" + pageNo);
				pageBar.append("</a>");
				pageBar.append("</li>");
			}else {
				pageBar.append("<li class='page-item'>");
				pageBar.append("<a class='page-link' href='javascript:fn_paging(" + pageNo + ")'>"+ pageNo);
				pageBar.append("</a>");
				pageBar.append("</li>");
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
//			String str="""
//					""".formatted(,,,,,,,);
			pageBar.append("<li class='page-item disabled'>");
			pageBar.append("<a class='page-link' href='#'>다음");
			pageBar.append("</a>");
			pageBar.append("</li>");
		}else {
			pageBar.append("<li class='page-item'>");
			pageBar.append("<a class='page-link' href='javascript:fn_paging(" + pageNo + ")'>다음");
			pageBar.append("</a>");
			pageBar.append("</li>");
		}
		pageBar.append("</ul>");
		
		// 스크립트문 추가
		pageBar.append("<script>");
		pageBar.append("function fn_paging(no){");
		//pageBar.append("location.assign('"+ url + "?cPage='+no+'&numPerpage="+ numPerpage +"');");
		pageBar.append("location.assign('"+ url + "?cPage='+no);");
		pageBar.append("}");
		pageBar.append("</script>");
		
		return new String(pageBar); // Stringbuffer를 String으로 형변환해서 반환
	}
}
