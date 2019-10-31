package guru.springframework.msscbeerservice.web.mappers;

/*
 * Created by arunabhamidipati on 31/10/2019
 */

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime asOffSetDateTime(Timestamp ts) {
        if (ts != null) {
            return OffsetDateTime.of(ts.toLocalDateTime().getYear()
                                    ,ts.toLocalDateTime().getMonthValue()
                                    ,ts.toLocalDateTime().getDayOfMonth()
                                    ,ts.toLocalDateTime().getHour()
                                    ,ts.toLocalDateTime().getMinute()
                                    ,ts.toLocalDateTime().getMinute()
                                    ,ts.toLocalDateTime().getNano()
                                    , ZoneOffset.UTC);
        } else {
            return null;
        }
    }


    public Timestamp asTimeStamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime != null) {
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        }else{
            return  null;
        }
    }
}
