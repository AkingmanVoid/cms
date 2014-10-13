package com.webpagebytes.cms;

import static org.junit.Assert.*;

import java.util.HashSet;

import javax.jws.WebParam;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.webpagebytes.cms.PublicContentServlet;
import com.webpagebytes.cms.WBServletUtility;
import com.webpagebytes.cms.appinterfaces.WBModel;
import com.webpagebytes.cms.cache.WBArticlesCache;
import com.webpagebytes.cms.cache.WBCacheInstances;
import com.webpagebytes.cms.cache.WBFilesCache;
import com.webpagebytes.cms.cache.WBMessagesCache;
import com.webpagebytes.cms.cache.WBParametersCache;
import com.webpagebytes.cms.cache.WBProjectCache;
import com.webpagebytes.cms.cache.WBUrisCache;
import com.webpagebytes.cms.cache.WBWebPageModulesCache;
import com.webpagebytes.cms.cache.WBWebPagesCache;
import com.webpagebytes.cms.cmsdata.WBWebPage;
import com.webpagebytes.cms.exception.WBIOException;
import com.webpagebytes.cms.utility.WBConfigurationFactory;

import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PublicContentServlet.class})
public class TestPublicContentServlet {

PublicContentServlet publicServlet;
HttpServletRequest requestMock;
HttpServletResponse responseMock;
WBCacheInstances cacheInstances;
WBUrisCache urisCacheMock;
WBParametersCache parametersCacheMock;
WBFilesCache filesCacheMock;
WBMessagesCache messagesCacheMock;
WBArticlesCache articlesCacheMock;
WBWebPagesCache pagesCacheMock;
WBWebPageModulesCache modulesCacheMock;
WBProjectCache projectCacheMock;


@Before
public void setUp()
{
publicServlet = new PublicContentServlet();

requestMock = EasyMock.createMock(HttpServletRequest.class);
responseMock = EasyMock.createMock(HttpServletResponse.class);

urisCacheMock = EasyMock.createMock(WBUrisCache.class);
parametersCacheMock = EasyMock.createMock(WBParametersCache.class);
filesCacheMock = EasyMock.createMock(WBFilesCache.class);
messagesCacheMock = EasyMock.createMock(WBMessagesCache.class);
articlesCacheMock = EasyMock.createMock(WBArticlesCache.class);
pagesCacheMock = EasyMock.createMock(WBWebPagesCache.class);
modulesCacheMock = EasyMock.createMock(WBWebPageModulesCache.class);
projectCacheMock = EasyMock.createMock(WBProjectCache.class);

cacheInstances = new WBCacheInstances(urisCacheMock, pagesCacheMock, modulesCacheMock, parametersCacheMock, filesCacheMock, articlesCacheMock, messagesCacheMock, projectCacheMock); 

Whitebox.setInternalState(WBConfigurationFactory.class, "configuration", (Object) null);
Whitebox.setInternalState(WBConfigurationFactory.class, "configPath", (Object) null);	

}

@After
public void tearDown()
{
	Whitebox.setInternalState(WBConfigurationFactory.class, "configuration", (Object) null);
	Whitebox.setInternalState(WBConfigurationFactory.class, "configPath", (Object) null);	
}

@Test
public void test_doGet()
{
	suppress(method(PublicContentServlet.class, "handleRequest"));
	try
	{
		publicServlet.doGet(requestMock, responseMock);
	}
	catch (Exception e)
	{
		assertTrue (false);
	}
}

@Test
public void test_doPut()
{
	suppress(method(PublicContentServlet.class, "handleRequest"));
	try
	{
		publicServlet.doPut(requestMock, responseMock);
	}
	catch (Exception e)
	{
		assertTrue (false);
	}
}

@Test
public void test_doPost()
{
	suppress(method(PublicContentServlet.class, "handleRequest"));
	try
	{
		publicServlet.doPost(requestMock, responseMock);
	}
	catch (Exception e)
	{
		assertTrue (false);
	}
}

@Test
public void test_doDelete()
{
	suppress(method(PublicContentServlet.class, "handleRequest"));
	try
	{
		publicServlet.doDelete(requestMock, responseMock);
	}
	catch (Exception e)
	{
		assertTrue (false);
	}
}

@Test
@SuppressStaticInitializationFor("java.util.logging.Logger")
public void test_init_exception()
{
	suppress(method(HttpServlet.class, "init"));
	
	ServletConfig configMock = EasyMock.createMock(ServletConfig.class);
	WBServletUtility servletUtilityMock = EasyMock.createMock(WBServletUtility.class);	
	EasyMock.expect(servletUtilityMock.getInitParameter("uri-prefix", publicServlet)).andReturn("/");	
	EasyMock.expect(servletUtilityMock.getContextParameter(PublicContentServlet.CMS_CONFIG_KEY, publicServlet)).andReturn("META-INF/wbconfiguration.xml");
	Whitebox.setInternalState(publicServlet, "servletUtility", servletUtilityMock);
	Whitebox.setInternalState(publicServlet, "cacheInstances", cacheInstances);
	
	try
	{
		EasyMock.expect(urisCacheMock.getAllUris(0)).andThrow(new WBIOException(""));
		EasyMock.replay(requestMock, responseMock, servletUtilityMock, configMock, urisCacheMock);
		publicServlet.init(configMock);
		
	} catch (ServletException e)
	{
		// OK
	}
	catch (Exception e)
	{
		assertTrue (false);
	}
	EasyMock.verify(requestMock, responseMock, servletUtilityMock, configMock);
		
}

@Test
@SuppressStaticInitializationFor("java.util.logging.Logger")
public void test_init()
{
	suppress(method(HttpServlet.class, "init"));
	
	ServletConfig configMock = EasyMock.createMock(ServletConfig.class);
	WBServletUtility servletUtilityMock = EasyMock.createMock(WBServletUtility.class);	
	EasyMock.expect(servletUtilityMock.getInitParameter("uri-prefix", publicServlet)).andReturn("/test");	
	EasyMock.expect(servletUtilityMock.getContextParameter(PublicContentServlet.CMS_CONFIG_KEY, publicServlet)).andReturn("META-INF/wbconfiguration.xml");
	
	Whitebox.setInternalState(publicServlet, "servletUtility", servletUtilityMock);
	Whitebox.setInternalState(publicServlet, "cacheInstances", cacheInstances);
	
	try
	{
		for(int i = 0; i<4; i++)
		{
			EasyMock.expect(urisCacheMock.getAllUris(i)).andReturn(new HashSet());
			EasyMock.expect(urisCacheMock.getCacheFingerPrint()).andReturn(1L);
		}
		
		EasyMock.replay(requestMock, responseMock, servletUtilityMock, configMock, urisCacheMock);
		publicServlet.init(configMock);
		
	} 
	catch (Exception e)
	{
		assertTrue (false);
	}
	EasyMock.verify(requestMock, responseMock, servletUtilityMock, configMock);
	
	
}

@Test
public void test_handleRequestTypeText()
{
	try
	{
	WBWebPage pageMock = EasyMock.createMock(WBWebPage.class);
	WBModel modelMock = EasyMock.createMock(WBModel.class);
	PageContentBuilder pageBuilderMock = EasyMock.createMock(PageContentBuilder.class);
	Whitebox.setInternalState(publicServlet, "pageContentBuilder", pageBuilderMock);
	String content = "aContent";
	EasyMock.expect(pageBuilderMock.buildPageContent(requestMock, pageMock, modelMock)).andReturn(content);
	responseMock.setCharacterEncoding("UTF-8");
	EasyMock.expect(pageMock.getIsTemplateSource()).andReturn(1);
	responseMock.addHeader("cache-control", "no-cache;no-store;");
	String contentType="plain/text";
	EasyMock.expect(pageMock.getContentType()).andReturn(contentType);	
	responseMock.setContentType(contentType);
	ServletOutputStream sos_ = EasyMock.createMock(ServletOutputStream.class);
	CacheServletOutputStream cacheOutputStream = new CacheServletOutputStream(sos_);
	EasyMock.expect(responseMock.getOutputStream()).andReturn(cacheOutputStream);
	Capture<byte[]> capture = new Capture<byte[]>();
	sos_.write(EasyMock.capture(capture));
	EasyMock.replay(requestMock, responseMock, pageMock, modelMock, sos_, pageBuilderMock);
	Whitebox.invokeMethod(publicServlet, "handleRequestTypeText", pageMock, requestMock, responseMock, modelMock);
	
	assertTrue((new String(capture.getValue())).equals(content));
	} catch (Exception e)
	{
		assertTrue(false);
	}
}

@Test
public void test_handleRequestTypeText_cache_0()
{
	handleRequestTypeText_cache(0);
}

@Test
public void test_handleRequestTypeText_cache_null()
{
	handleRequestTypeText_cache(null);
}

private void handleRequestTypeText_cache(Integer templateSource)
{
	try
	{
	WBWebPage pageMock = EasyMock.createMock(WBWebPage.class);
	WBModel modelMock = EasyMock.createMock(WBModel.class);
	PageContentBuilder pageBuilderMock = EasyMock.createMock(PageContentBuilder.class);
	Whitebox.setInternalState(publicServlet, "pageContentBuilder", pageBuilderMock);
	String content = "aContent";
	EasyMock.expect(pageBuilderMock.buildPageContent(requestMock, pageMock, modelMock)).andReturn(content);
	responseMock.setCharacterEncoding("UTF-8");
	EasyMock.expect(pageMock.getIsTemplateSource()).andReturn(0);
	
	Long hash = 123L;
	EasyMock.expect(requestMock.getParameter(PublicContentServlet.CACHE_QUERY_PARAM)).andReturn(hash.toString());
	EasyMock.expect(pageMock.getHash()).andReturn(hash);
	
	responseMock.addHeader("cache-control", "max-age=86400");
	String contentType="plain/text";
	EasyMock.expect(pageMock.getContentType()).andReturn(contentType);	
	responseMock.setContentType(contentType);
	ServletOutputStream sos_ = EasyMock.createMock(ServletOutputStream.class);
	CacheServletOutputStream cacheOutputStream = new CacheServletOutputStream(sos_);
	EasyMock.expect(responseMock.getOutputStream()).andReturn(cacheOutputStream);
	Capture<byte[]> capture = new Capture<byte[]>();
	sos_.write(EasyMock.capture(capture));
	EasyMock.replay(requestMock, responseMock, pageMock, modelMock, sos_, pageBuilderMock);
	Whitebox.invokeMethod(publicServlet, "handleRequestTypeText", pageMock, requestMock, responseMock, modelMock);
	
	assertTrue((new String(capture.getValue())).equals(content));
	} catch (Exception e)
	{
		assertTrue(false);
	}
}


@Test
public void test_handleRequestTypeText_no_page()
{
	try
	{
	WBModel modelMock = EasyMock.createMock(WBModel.class);
	responseMock.setStatus(HttpServletResponse.SC_NOT_FOUND);
	EasyMock.replay(requestMock, responseMock, modelMock);
	Whitebox.invokeMethod(publicServlet, "handleRequestTypeText", (WBWebPage)null, requestMock, responseMock, modelMock);

	} catch (Exception e)
	{
		assertTrue(false);
	}
}

}
