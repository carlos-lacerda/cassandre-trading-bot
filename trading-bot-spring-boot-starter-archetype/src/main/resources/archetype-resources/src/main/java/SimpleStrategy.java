#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import tech.cassandre.trading.bot.dto.market.TickerDTO;
import tech.cassandre.trading.bot.dto.trade.OrderDTO;
import tech.cassandre.trading.bot.dto.user.AccountDTO;
import tech.cassandre.trading.bot.strategy.BasicStrategy;
import tech.cassandre.trading.bot.strategy.Strategy;
import tech.cassandre.trading.bot.util.dto.CurrencyDTO;
import tech.cassandre.trading.bot.util.dto.CurrencyPairDTO;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Simple strategy.
 * Please, create your own Kucoin sandbox account and do not make orders with this account.
 * How to do it : https://trading-bot.cassandre.tech/how_to_create_an_exchange_sandbox_for_kucoin.html
 */
@Strategy(name = "Simple strategy")
public final class SimpleStrategy extends BasicStrategy {

	/** The accounts owned by the user. */
	private final Map<String, AccountDTO> accounts = new LinkedHashMap<>();

	@Override
	public Set<CurrencyPairDTO> getRequestedCurrencyPairs() {
		// We only ask about ETC/BTC (Base currency : ETH / Quote currency : BTC).
		return Set.of(new CurrencyPairDTO(CurrencyDTO.ETH, CurrencyDTO.BTC));
	}

	@Override
	public void onAccountUpdate(final AccountDTO account) {
		// Here, we will receive an AccountDTO each time there is a move on our account.
		System.out.println("Received information about an account : " + account);
		accounts.put(account.getId(), account);
	}

	@Override
	public void onTickerUpdate(final TickerDTO ticker) {
		// Here we will receive a TickerDTO each time a new one is available.
		System.out.println("Received information about a ticker : " + ticker);
	}

	@Override
	public void onOrderUpdate(final OrderDTO order) {
		// Here, we will receive an OrderDTO each an Order data has changed in the exchange.
		System.out.println("Received information about an order : " + order);
	}

	/**
	 * Getter accounts.
	 *
	 * @return accounts
	 */
	public Map<String, AccountDTO> getAccounts() {
		return accounts;
	}

}
