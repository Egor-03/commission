package by.grsu.anikevich.comission.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;

import by.grsu.anikevich.comission.web.dto.TableStateDto;

public abstract class AbstractListServlet extends HttpServlet {

	protected TableStateDto resolveTableStateDto(final HttpServletRequest req, int totalCount) {
		final String keyTableStateDto = getClass().getSimpleName() + "_TableStateDto";

		TableStateDto tableStateDto = (TableStateDto) req.getSession().getAttribute(keyTableStateDto);
		if (tableStateDto == null) {
			tableStateDto = new TableStateDto(5);
			req.getSession().setAttribute(keyTableStateDto, tableStateDto);
		}

		tableStateDto.setTotalCount(totalCount);

		String pageAttr = req.getParameter("page");
		if (!Strings.isNullOrEmpty(pageAttr)) {
			tableStateDto.setPage(Integer.parseInt(pageAttr));
		}

		String sortColumnAttr = req.getParameter("sort");
		if (!Strings.isNullOrEmpty(sortColumnAttr)) {
			tableStateDto.setSort(sortColumnAttr, "id");
		}

		req.setAttribute(TableStateDto.KEY_CURRENT_PAGE_TABLE_STATE, tableStateDto);
		return tableStateDto;
	}

}
