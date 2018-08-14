package pl.srslycpp.myWeb.Service;

import org.springframework.stereotype.Service;
import pl.srslycpp.myWeb.commands.UnitOfMeasureCommand;

import java.util.Set;

@Service
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
