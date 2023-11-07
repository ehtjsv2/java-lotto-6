package lotto.model;

import java.util.EnumMap;
import java.util.List;

public class Customer {
    private List<Lotto> lottos;
    private final EnumMap<Ranking, Integer> rankingCount;

    public Customer() {
        rankingCount = new EnumMap<>(Ranking.class);
        for (Ranking ranking : Ranking.values()) {
            rankingCount.put(ranking, 0);
        }
    }

    public void buyLotto(Money money) {
        this.lottos = LottoGenerator.issueLottoTickets(money);
    }

    public int getLottoCount() {
        return lottos.size();
    }

    public String getLottosString() {
        StringBuilder sb = new StringBuilder();
        lottos.forEach((lotto) -> sb.append(lotto.toString()).append("\n"));
        return sb.toString();
    }

    public void matchLotto(WinLotto winLotto) {
        for (Lotto lotto : lottos) {
            Ranking ranking = winLotto.findOutLottoRanking(lotto);
            updateRankingCount(ranking);
        }
    }

    private void updateRankingCount(Ranking ranking) {
        rankingCount.put(ranking, rankingCount.get(ranking) + 1);
    }

    public String getMatchResult() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Ranking ranking : Ranking.values()) {
            if (ranking == Ranking.NONE) {
                continue;
            }
            stringBuilder.append(ranking.getMatchedCount()).append("개 일치");
            if (ranking == Ranking.SECOND) {
                stringBuilder.append(", 보너스 볼 일치");
            }
            stringBuilder.append(" (").append(ranking.getWinnings())
                    .append(") - ").append(rankingCount.get(ranking)).append("개\n");

        }
        return stringBuilder.toString();
    }


}
