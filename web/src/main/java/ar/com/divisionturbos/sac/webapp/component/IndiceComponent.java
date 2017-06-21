package ar.com.divisionturbos.sac.webapp.component;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by mzanetti on 28/09/16.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndiceComponent implements Serializable {

   private Long itm = 0L;

    public Long getItm() {
        itm ++;
        return itm;
    }

    public void setItm(Long itm) {
        this.itm = itm;
    }
}
